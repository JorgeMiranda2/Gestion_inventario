import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AdministrationComponent } from './components/administration/administration.component';
import { SuppliersComponent } from './components/administration/suppliers/suppliers.component';
import { CategoriesComponent } from './components/administration/categories/categories.component';
import { ProductsComponent } from './components/administration/products/products.component';


const routes: Routes = [
    { path: '', component:HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'register', component:RegisterComponent },
    { path: 'administration', component: AdministrationComponent,
    children:[
        { path: 'suppliers', component:SuppliersComponent },
        { path: 'categories', component:CategoriesComponent },
        { path: 'products', component:ProductsComponent },
    ]
},
  ];

  export {routes};
