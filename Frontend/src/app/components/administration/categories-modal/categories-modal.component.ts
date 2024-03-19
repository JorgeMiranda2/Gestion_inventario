import { Component, Inject, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ObtainHeader } from '../../../helpers/obtainHeader';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BASE_BACKEND_URL } from '../../../enviroment';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-categories-modal',
  standalone: true,
  imports: [CommonModule,FormsModule,MatFormFieldModule, MatSelectModule,ReactiveFormsModule],
  templateUrl: './categories-modal.component.html',
  styleUrl: './categories-modal.component.css'
})
export class CategoriesModalComponent {



  constructor(private http: HttpClient,@Inject(MAT_DIALOG_DATA) public data: any, private obtainHeader: ObtainHeader) {}


  modalData = {
    name: '',
    status: null,
  };


  ngOnInit() {




        if(this.data.selectedData){
          
          this.modalData = {
            name: this.data.selectedData?.name,
            status: this.data.selectedData?.status,

          }


        
        }
       



      }
   


  @Output() closeModal: EventEmitter<void> = new EventEmitter<void>();
  @Output() submitData: EventEmitter<any> = new EventEmitter<any>();

  onCloseModal() {
    
    this.closeModal.emit();
  }
  onSubmit() {    
    const sendData = {...this.modalData}
    this.submitData.emit(sendData);
    this.onCloseModal();
  }
}
