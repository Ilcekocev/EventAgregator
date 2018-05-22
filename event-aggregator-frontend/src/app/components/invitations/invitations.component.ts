import { Component, OnInit } from '@angular/core';
import {InviteService} from "../../services/invite.service";
import {EventService} from "../../services/event.service";
import {EventDTO} from "../../model/EventDTO";
import {AuthService} from "../../services/auth.service";
import {FormControl, Validators} from "@angular/forms";
import {InviteDTO} from "../../model/InvitationDTO";

@Component({
  selector: 'app-invitations',
  templateUrl: './invitations.component.html',
  styleUrls: ['./invitations.component.css']
})
export class InvitationsComponent implements OnInit {

  events: EventDTO[];
  userId: string;
  emailFormControl: FormControl;
  numberOfColumns: number;


  constructor(private inviteService: InviteService,
              private eventService: EventService,
              private authService: AuthService) { }

  ngOnInit() {
    this.emailFormControl = new FormControl('', [
      Validators.required,
      Validators.email,
    ]);

    this.userId = this.authService.currentUser.id;
    this.eventService.fetchAllPublicAfterNow(this.authService.currentUser.id)
      .subscribe(data => {
        console.log("Data {}", data);
        this.events = data;
        this.numberOfColumns = this.calculateColumns();
      });
  }

  onSend(eventId: number) {
    const inviteDTO: InviteDTO = {
      eventId: eventId,
      personEmail: this.emailFormControl.value
    };
    console.log(inviteDTO);
    this.inviteService.sendInvitation(inviteDTO)
      .subscribe(data => console.log("Succesfully invited"));
  }


  calculateColumns(): number {
    if(this.events.length >= 6)
      return 6;
    return this.events.length;
  }

}
