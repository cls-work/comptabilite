import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../_services/authentication.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {

  addUserForm: FormGroup;
  constructor(private authenticationService: AuthenticationService,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.addUserForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required],
      name: ['', Validators.required],
      role: ['1', Validators.required]
    });
  }

  addUser() {
    this.addUserForm.value.role = parseInt(this.addUserForm.value.role, 10);
    this.authenticationService.addUser(this.addUserForm.value)
      .subscribe(d => {
        console.log(d);
      });
  }
}
