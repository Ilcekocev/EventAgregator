import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {User} from "../../model/User";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  user: User;

  constructor(private authService: AuthService) { }

  ngOnInit() {
   if(this.authService.validateAuthentication())
     this.authService.fetchUserObject()
       .subscribe((data: User) => {
         console.log("Printing the fetched user {}", data);
         this.authService.currentUser = data;
         this.user = data;
       });
  }

}
