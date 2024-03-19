import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatTableModule} from '@angular/material/table';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import {HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ObtainHeader } from '../../../helpers/obtainHeader';
import { BASE_BACKEND_URL } from '../../../enviroment';
import { SuppliersModalComponent } from '../suppliers-modal/suppliers-modal.component';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';


@Component({
  selector: 'app-suppliers',
  standalone: true,
  imports: [CommonModule, MatTableModule, HttpClientModule, MatDialogModule],
  templateUrl: './suppliers.component.html',
  styleUrl: './suppliers.component.css'
})
export class SuppliersComponent implements OnInit{


  
  constructor(private http: HttpClient, private dialog: MatDialog, private obtainHeader : ObtainHeader) {}


 loadDestinations(): Observable<any[]> {
    const headers = this.obtainHeader.getAuthHeader();

    return this.http
      .get<any[]>(`${BASE_BACKEND_URL}/api/supplier`, { headers })
  
  }

  ngOnInit() {
   this.loadDestinations().subscribe(
    (response) => {
      console.log(response);
      this.dataSource=response;
    },
    (error) => {
      console.log(error);
    }
   )
  }



  displayedColumns: string[] = ['id', 'nombre', 'numero de cuenta', 'estado'];
  dataSource: any[] = [];
  selectedRow: any | null = null;

onRowClicked(row: any) {
  this.selectedRow = row;

  
}

selectedData: any | null = null;
isUpdate: boolean = false;

openModal() {
  const dialogRef = this.dialog.open(SuppliersModalComponent, {
    width: '800px',
    data: { selectedData: this.selectedData}
  });

  dialogRef.componentInstance.closeModal.subscribe(() => {
    dialogRef.close(); // Cierra el modal interno desde el padre
  });
  dialogRef.componentInstance.submitData.subscribe((data) => {
    this.onSubmitData(data); // Función para manejar los datos enviados desde el modal
    dialogRef.close(); // Cierra el modal
  });
}

onAdd(){
  this.selectedData = null;
  this.isUpdate = false;
this.openModal()
}

onUpdate(){

  if (this.selectedRow) {
    this.selectedData = { ...this.selectedRow }; // Guarda la información de la fila seleccionada
    this.isUpdate = true; // Establece la bandera de actualización a true
  }

  this.openModal()
}
onDelete(id:number | undefined){
  if(id){
    const headers = this.obtainHeader.getAuthHeader();
    this.http
    .delete<any>(`${BASE_BACKEND_URL}/api/supplier/${id}`,{ headers }).subscribe((res)=>{
      console.log(res);
      this.loadDestinations().subscribe((response)=>{
       this.dataSource=response;
      }, (error)=>{
        console.log("error");
      });
    },
    (error) => {
      console.error('deleting error:', error);
      // Manejar el error si es necesario
    })
  }

}




  downloadPDF() {
    const table = document.querySelector('.table'); // Selecciona tu tabla

    if (table instanceof HTMLElement) {
      html2canvas(table).then((canvas) => {
        const imgData = canvas.toDataURL('image/png');
      const pdf = new jsPDF();
      const imgProps = pdf.getImageProperties(imgData);
      const pdfWidth = pdf.internal.pageSize.getWidth();
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      
      pdf.addImage(imgData, 'PNG', 0, 0, pdfWidth, pdfHeight);
      pdf.save('Proveedores.pdf');
      });
    } else {
      console.error('No se encontró la tabla');
    }
  }


onSubmitData(data: any):void {
  const headers = this.obtainHeader.getAuthHeader();

  if (this.isUpdate && this.selectedRow) {
    console.log(data, this.isUpdate);
    console.log(this.selectedRow.id, "xxs");
    this.http
      .put<any>(`${BASE_BACKEND_URL}/api/supplier/${this.selectedRow.id}`, data,{ headers }).subscribe((res)=>{
        console.log(res);
        this.loadDestinations().subscribe((response)=>{ this.dataSource=response;});
      },
      (error) => {
        console.error('Create error:', error);
        // Manejar el error si es necesario
      })

  } else {
    console.log(data, this.isUpdate);
    console.log(headers);
    this.http
      .post<any>(`${BASE_BACKEND_URL}/api/supplier`, data,{ headers }).subscribe((res)=>{
        console.log(res);
        this.loadDestinations().subscribe((response)=>{ this.dataSource=response;});
      },
      (error) => {
        console.error('Create error:', error);
        // Manejar el error si es necesario
      })
  }
}

}
