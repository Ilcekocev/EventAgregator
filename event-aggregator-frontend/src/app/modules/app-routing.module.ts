import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "../components/login/login.component";
import {HomePageComponent} from "../components/home-page/home-page.component";
import {DashboardComponent} from "../components/dashboard/dashboard.component";
import {AuthGuard} from "../guards/auth.guard";
import {InvitationsComponent} from "../components/invitations/invitations.component";

const routes: Routes = [
  {path: '', redirectTo: "home", pathMatch: 'full'},
  {path: 'home', component: HomePageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  {path: 'invitations', component: InvitationsComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  declarations: [],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
