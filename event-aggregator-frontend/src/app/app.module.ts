import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {
  MatButtonModule,
  MatCardModule,
  MatGridListModule,
  MatIconModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatSidenavModule,
  MatToolbarModule
} from "@angular/material";
import {MaterialModuleModule} from "./modules/material-module.module";
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {LoginComponent} from './components/login/login.component'
import {AppRoutingModule} from "./modules/app-routing.module";
import {LayoutModule} from '@angular/cdk/layout';
import {HomePageComponent} from './components/home-page/home-page.component';
import {UserService} from "./services/user.service";
import {NavBarComponent} from './components/nav-bar/nav-bar.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {EventDetailsComponent} from './components/event-details/event-details.component';
import {EventListComponent} from './components/event-list/event-list.component';
import {CreateEventComponent} from './components/create-event/create-event.component';
import {UpdateEventComponent} from './components/update-event/update-event.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomePageComponent,
    NavBarComponent,
    DashboardComponent,
    EventDetailsComponent,
    EventListComponent,
    CreateEventComponent,
    UpdateEventComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MaterialModuleModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    AngularFontAwesomeModule,
    AppRoutingModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
