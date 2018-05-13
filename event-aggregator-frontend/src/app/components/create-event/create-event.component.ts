import {Component, Input, OnInit} from '@angular/core';
import {Event} from "../../model/Event";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import * as moment from 'moment';

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.css']
})
export class CreateEventComponent implements OnInit {

  options: FormGroup;

  @Input()
  create: boolean;

  @Input()
  event: Event;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
   if(this.create) {
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
      emailNotification:['true']
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
      emailNotification:[this.event.emailNotification.toString()]
    })
  }

  submitForm() {
    const formData = this.buildFormData();
    if(this.create) {
      //eventService.addNewEvent(formData, user.email);
      console.log("creating event");
    }
    else {
      //eventService.editEvent(formData, event.id);
      console.log("editing event");
    }
  }

  deleteEvent() {
    //this.eventService.delete(event.id);
    console.log("deleting event");
  }

  buildFormData(): FormData {
    let formData = new FormData();
    let formValue = this.options.value;
    formData.set('title', formValue.title);
    formData.set('description', formValue.description);
    formData.set('type', formValue.type.toUpperCase());
    formData.set('startTime', moment(formValue.startTime).toISOString());
    formData.set('endTime', moment(formValue.endTime).toISOString());
    formData.set('externalLink', formValue.externalLink);
    formData.set('emailNotification', formValue.emailNotification);
    return formData;
  }

}
