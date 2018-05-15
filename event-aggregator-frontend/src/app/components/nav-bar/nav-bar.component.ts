import {Component} from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  constructor (private authService: AuthService) {

  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated
  }

}
