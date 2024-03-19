import { Component, OnInit, Output, EventEmitter, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { BASE_BACKEND_URL } from '../../../enviroment';
import { FormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ObtainHeader } from '../../../helpers/obtainHeader';


@Component({
  selector: 'app-suppliers-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './suppliers-modal.component.html',
  styleUrl: './suppliers-modal.component.css'
})
export class SuppliersModalComponent implements OnInit {

  constructor(private http: HttpClient, @Inject(MAT_DIALOG_DATA) public data: any, private obtainHeader: ObtainHeader) { }

  supplier = {
    id : null,
    name:null,
    accountNumber : null,
    status:null
  }

  ngOnInit() {


    if (this.data.selectedData) {
      this.supplier = {
        id : this.data.selectedData?.id,
        name:this.data.selectedData?.name,
        accountNumber : this.data.selectedData?.accountNumber,
        status:this.data.selectedData?.status
      }

    
    }
  }


  @Output() closeModal: EventEmitter<void> = new EventEmitter<void>();
  @Output() submitData: EventEmitter<any> = new EventEmitter<any>();

  onCloseModal() {
    this.closeModal.emit();
  }

  onSubmit() {
    this.submitData.emit(this.supplier);
    this.onCloseModal();
  }
}
