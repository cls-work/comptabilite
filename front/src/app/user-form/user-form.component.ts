import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../_services/authentication.service';
import {UserModel} from '../_models/user.model';
import {UserService} from '../_services/user.service';
import {ActivatedRoute, Router} from '@angular/router';

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
      this.loading=true;
      this.userService.getUserById(id)
        .subscribe(user => {
          this.user = user;
          console.log(user);
          // @ts-ignore
          this.userForm = this.initUserForm(this.user.name, this.user.username, this.user.email, this.user.roles[0].id.toString());
          this.loading=false
        });
    }

    this.roles = this.userService.roles;
  }

  initUserForm(name: string, username: string, email: string, role: string) {
    return this.fb.group({
      username: [username , Validators.required],
      email: [email , Validators.compose([
        Validators.required,
        Validators.email]
      )],
      password: ['', Validators.required],
      repeatPassword: ['', name !== '' ? Validators.required : null],
      name: [name , Validators.required],
      roleId: [role, Validators.required]
    });
  }

  addUser() {
    this.loading=true;
    this.userForm.value.roleId = parseInt(this.userForm.value.roleId, 10);
    this.userService.addUser(this.userForm.value)
      .subscribe(d => {
        console.log(d);
        this.loading=false;
        this.router.navigate(['/users']);
      });
  }

  editUser() {
    this.loading=true
    this.userForm.value.roleId = parseInt(this.userForm.value.roleId, 10);
    this.userService.editUser(this.userForm.value, this.user.id)
      .subscribe(d => {
        console.log(d);
        this.loading=false
        this.router.navigate(['/users']);
      });
  }

  checkEqualPasswords() {
    return this.userForm.value.password === this.userForm.value.repeatPassword;
  }

  getAllRoles() {
    this.userService.getAllRoles()
      .subscribe(roles => {
        this.roles = roles;
        console.log(roles);
      });
  }


}
