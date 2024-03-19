import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ObtainHeader } from '../../../helpers/obtainHeader';
import { HttpClient } from '@angular/common/http';
import { BASE_BACKEND_URL } from '../../../enviroment';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-products-modal',
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatSelectModule, ReactiveFormsModule],
  templateUrl: './products-modal.component.html',
  styleUrls: ['./products-modal.component.css']
})
export class ProductsModalComponent {

  constructor(private http: HttpClient, @Inject(MAT_DIALOG_DATA) public data: any, private obtainHeader: ObtainHeader) {}

  categories: any[] = [];
  cities: any[] = [];

  product: any = {
    name: '',
    description: null,
    categoryType: null,
    storageLocation: '',
    productCode: '',
    quantity: null
  };

  ngOnInit() {
    const headers = this.obtainHeader.getAuthHeader();
    console.log("modal data: ",this.data.selectedData )
    if(this.data.selectedData){
          
      this.product = {
        name: this.data.selectedData?.name,
        description: this.data.selectedData?.description,
        categoryType: this.data.selectedData?.category?.name,
        storageLocation: this.data.selectedData?.storageLocation,
        productCode: this.data.selectedData?.productCode,
        quantity: this.data.selectedData?.quantity

      }
      const categoryName = this.data.selectedData?.category?.name;
    if (categoryName) {
        const matchingCategory = this.categories.find(category => category.name === categoryName);
        if (matchingCategory) {
            this.product.productType = matchingCategory.id;
        }
    }

    
    }

    //obtener categorias
    this.http.get<any>(`${BASE_BACKEND_URL}/api/category`, { headers }).subscribe(
      (res) => {
        console.log(res);
        this.categories = res;
        console.log("categoriasss: ", res );
   
      },
      (error) => {
        console.log("error: ", error);
   
      }
    );
  }

  @Output() closeModal: EventEmitter<void> = new EventEmitter<void>();
  @Output() submitData: EventEmitter<any> = new EventEmitter<any>();

  onCloseModal() {
    this.closeModal.emit();
  }

  onSubmit() {
    var convertedData = {
        name: this.product?.name,
        description: this.product?.description,
        storageLocation: this.product?.storageLocation,
        productCode: this.product?.productCode,
        quantity: this.product?.quantity,
        category: {
          id: this.product?.categoryType,
        }
    }
    this.submitData.emit(convertedData);
    this.onCloseModal();
  }
}
