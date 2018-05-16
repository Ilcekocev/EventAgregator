import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {User} from "../../model/User";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  currentUser: User;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    if(this.authService.validateAuthentication()) {
      console.log("Auth: {}\n Here: {}", this.authService.currentUser, this.currentUser);
      this.currentUser = this.authService.currentUser;
      console.log("Auth: {}\n Here: {}", this.authService.currentUser, this.currentUser);
    }
  }

}
