import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {AuthService} from "../../services/auth.service";
import {User} from "../../model/User";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private userService: UserService, private authService: AuthService) { }

  user: User;

  ngOnInit() {
    this.getUser();
    this.authService.isUserAuth();
  }

  getUser() {
    this.userService.getAuthentication()
      .subscribe(data => {
        console.log("Printing auth: {}", data);
      });
    this.userService.getUser()
      .subscribe(data => {
        console.log("Printing user {}", data);
        this.user = data;
        console.log(this.user);
        localStorage.setItem('email', data.email);
      })
  }

  sendUser() {
    this.authService.sendUser(this.user)
      .subscribe(data => console.log(data));
  }

}
