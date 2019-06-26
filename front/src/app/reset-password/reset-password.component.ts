import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../_services/user.service';
import {map} from 'rxjs/operators';
import {passValidator} from "../_validators/pass.validator";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {

  sendEmailForgottenPasswordForm: FormGroup;
  resetPasswordForm: FormGroup;
  token: string;
  validToken;
  emailSent;
  passwordReset;
  loading;
  samePassword;

  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.token = this.route.snapshot.params.token;
    this.validToken = false;
    this.loading = false;
    if (this.token) {
      (this.verifyToken());

    }


    this.sendEmailForgottenPasswordForm = this.formBuilder.group({
        email: ['', Validators.compose([Validators.email])]
      });
    console.log('!token');
    this.resetPasswordForm = this.formBuilder.group({

        password: ['', Validators.required, ],
        confirmPassword: ['', passValidator]
      });
  }

  getToken() {
    this.emailSent = null;
    this.loading = true;
    if (!this.token) {
      this.userService.getToken(this.sendEmailForgottenPasswordForm.value.email)
        .subscribe(data => {
          console.log(data);
          console.log('data');
          // @ts-ignore
          this.emailSent = data.success;
          this.loading = false;
          console.log(this.emailSent);
        }, error => {
          console.log(error);
          console.log('error');
          // @ts-ignore
          this.emailSent = false;
          this.loading = false;

        });
    }
  }

  confirmPassword(): boolean {
    console.log(this.resetPasswordForm.value.password, this.resetPasswordForm.value.confirmPassword);
    return this.resetPasswordForm.value.password === this.resetPasswordForm.value.confirmPassword;
  }

  verifyToken() {
    this.userService.verifyToken(this.token)
      .subscribe(data => {
        console.log(data);
        // @ts-ignore
        this.validToken = ( data.success);
      }, error => {
        console.log(error);
      });

  }

  resetPassword() {
    this.loading = true;
    this.passwordReset = null;
    this.samePassword = this.confirmPassword();
    if (!this.samePassword) {
      console.log('not confirm password');
      this.loading = false;
      return;
    }
    this.userService.resetPassword( this.resetPasswordForm.value.password, this.token)
      .subscribe(data => {
        console.log(data);
        this.passwordReset = true;
        this.loading = false;
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 3000);
      }, error => {
        console.log(error);
        this.passwordReset = false;
        this.loading = false;
      });
  }

}
