import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../_services/authentication.service';
import {UserModel} from '../_models/user.model';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {

  userForm: FormGroup;
  @Input() user: UserModel;
  constructor(private authenticationService: AuthenticationService,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.userForm = this.fb.group({
      username: [this.user ? this.user.username : '', Validators.required],
      email: [this.user ? this.user.email : '', Validators.required, Validators.email],
      password: ['', Validators.required],
      repeatPassword: ['', this.user ? Validators.required : null],
      name: [this.user ? this.user.name : '', Validators.required],
      role: [this.user ? this.user.role.toString : '1', Validators.required]
    });
  }

  addUser() {
    this.userForm.value.role = parseInt(this.userForm.value.role, 10);
    this.authenticationService.addUser(this.userForm.value)
      .subscribe(d => {
        console.log(d);
      });
  }

  editUser() {
    this.userForm.value.role = parseInt(this.userForm.value.role, 10);
    this.authenticationService.addUser(this.userForm.value)
      .subscribe(d => {
        console.log(d);
      });
  }


}
