import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../_services/authentication.service';
import {UserModel} from '../_models/user.model';
import {UserService} from '../_services/user.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-add-user',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {

  userForm: FormGroup;
  user: UserModel;
  constructor(private authenticationService: AuthenticationService,
              private userService: UserService,
              private route: ActivatedRoute,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required],
      repeatPassword: '',
      name: ['', Validators.required],
      role: ['1', Validators.required]
    });

    const id = this.route.snapshot.params.id;
    if (id) {
      this.userService.getUserById(id)
        .subscribe(user => {
          this.user = user;
          console.log(user)
          this.userForm = this.fb.group({
            username: [this.user.username , Validators.required],
            email: [this.user.email , Validators.compose([
              Validators.required,
              Validators.email]
            )],
            password: ['', Validators.required],
            repeatPassword: ['', Validators.required],
            name: [this.user.name , Validators.required],
            role: ['', Validators.required]
          });
        });
    }
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
