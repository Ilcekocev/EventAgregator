import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Event} from "../../model/Event";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import * as moment from 'moment';
import {User} from "../../model/User";
import {EventService} from "../../services/event.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.css']
})
export class CreateEventComponent implements OnInit {

  options: FormGroup;

  @Input()
  create: boolean;

  user: User;

  @Input()
  event: Event;

  constructor(private fb: FormBuilder, private eventService: EventService, private authService: AuthService) {
  }

  ngOnInit() {
    this.user = this.authService.currentUser;
    if (this.create) {
      this.createForm();
    }
    else if (!this.create) {
      this.editForm();
    }
  }


  createForm() {
    this.options = this.fb.group({
      title: ['', Validators.required],
      type: ['private', Validators.required],
      description: ['', Validators.required],
      startTime: ['', Validators.required],
      endTime: ['', Validators.required],
      externalLink: [''],
      emailNotification: ['true']
    })
  }

  editForm() {
    this.options = this.fb.group({
      title: [this.event.title, Validators.required],
      type: [this.event.type.toLowerCase(), Validators.required],
      description: [this.event.description, Validators.required],
      startTime: [this.event.startTime, Validators.required],
      endTime: [this.event.endTime, Validators.required],
      externalLink: [this.event.externalLink],
      emailNotification: [this.event.emailNotification.toString()]
    })
  }

  submitForm() {
    this.bindFormDataToEvent();
    if (this.create) {
      this.eventService.createEvent(this.event).subscribe(data => {
        console.log("data: {}", data);
        this.event = data
      });
      console.log("creating event {}", this.event);
    }
    else {
      console.log("updating event {}", this.event);
      this.eventService.updateEvent(this.event).subscribe(data => {
        this.event = data;
        console.log("edited event {}", this.event);
      });
    }
  }

  deleteEvent() {
    console.log("deleting event: {}", this.event);
    this.eventService.deleteEvent(this.event.id)
      .subscribe();
    this.eventService.onDeleted.emit(true);
  }

  bindFormDataToEvent() {
    if (this.create) {
      this.event = new Event();
    }
    let formValue = this.options.value;
    this.event.title = formValue.title;
    this.event.description = formValue.description;
    this.event.type = formValue.type.toUpperCase();
    this.event.startTime = moment(formValue.startTime).format('YYYY-MM-DDTHH:mm:ss');
    this.event.endTime = moment(formValue.endTime).format('YYYY-MM-DDTHH:mm:ss');
    this.event.externalLink = formValue.externalLink;
    this.event.emailNotification = formValue.emailNotification;
    console.log(this.user);
    this.event.user = this.user;
  }

}
