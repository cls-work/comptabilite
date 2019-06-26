import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../_services/authentication.service';
import {UserModel} from '../_models/user.model';
import {UserService} from '../_services/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {passValidator} from '../_validators/pass.validator';

@Component({
  selector: 'app-add-user',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {

  userForm: FormGroup;
  user: UserModel;
  roles: any[];

  loading: boolean;
  error: boolean;
  submitted: boolean;

  constructor(private authenticationService: AuthenticationService,
              private userService: UserService,
              private route: ActivatedRoute,
              private router: Router,
              private fb: FormBuilder) { }

  ngOnInit() {
    if (!this.userService.roles) {
      this.router.navigate(['/users']);
      return;
    }
    this.userForm = this.initUserForm('', '', '', this.userService.roles[0].id.toString());

    const id = this.route.snapshot.params.id;
    if (id) {
      this.loading = true;
      this.userService.getUserById(id)
        .subscribe(user => {
          this.user = user;

          // @ts-ignore
          this.userForm = this.initUserForm(this.user.name, this.user.username, this.user.email, this.user.roles[0].id.toString());
          this.loading = false;
        }, () => {
          this.error = true;
          this.loading = false;
        });
    }

    this.roles = this.userService.roles;
  }

  initUserForm(name: string, username: string, email: string, role: string) {
    return this.fb.group({
      username: [username , Validators.compose(
        [Validators.minLength(5), Validators.required])],
      email: [email , Validators.compose([
        Validators.required,
        Validators.email]
      )],
      password: ['' , Validators.compose(
        [Validators.minLength(6), Validators.required])],
      repeatPassword: ['',  Validators.compose(
        [Validators.minLength(6), Validators.required, passValidator])],
      name: [name , Validators.compose(
        [Validators.minLength(5), Validators.required])],


      roleId: [role, Validators.required]
    });
  }

  addUser() {
    this.loading = true;
    this.submitted = true;
    this.userForm.value.roleId = parseInt(this.userForm.value.roleId, 10);
    this.userService.addUser(this.userForm.value)
      .subscribe(d => {

        this.loading = false;
        this.router.navigate(['/users/', 'added']);
      }, () => {
        this.loading = false;
        this.error = true;
      });
  }

  editUser() {
    this.loading = true;
    this.submitted = true;
    this.userForm.value.roleId = parseInt(this.userForm.value.roleId, 10);
    this.userService.editUser(this.userForm.value, this.user.id)
      .subscribe(d => {

        this.loading = false;
        this.router.navigate(['/users/', 'edited']);
      }, () => {
        this.loading = false;
        this.error = true;
      });
  }

  checkEqualPasswords() {
    return this.userForm.value.password === this.userForm.value.repeatPassword;
  }

  getAllRoles() {
    this.userService.getAllRoles()
      .subscribe(roles => {
        this.roles = roles;

      });
  }


}
