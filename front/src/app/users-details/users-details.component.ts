import { Component, OnInit } from '@angular/core';
import {UserModel} from '../_models/user.model';
import {UserService} from '../_services/user.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-users-details',
  templateUrl: './users-details.component.html',
  styleUrls: ['./users-details.component.scss']
})
export class UsersDetailsComponent implements OnInit {
  users: UserModel[];

  loading: boolean;
  userDeleted: boolean;
  userEdited: boolean;
  userAdded: boolean;
  error: boolean;

  constructor(private userService: UserService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers() {
    this.loading = true;
    this.userService.getAllUsers()
      .subscribe(users => {
        this.users = users;

        this.loading = false;
      }, () => {
        this.error = true;
        this.loading = false;
      });
    if (this.route.snapshot.params.success === 'added') {
      this.userAdded = true;
    }
    if (this.route.snapshot.params.success === 'edited') {
      this.userEdited = true;
    }
  }


  deleteUser(id: string) {
    this.loading = true;
    if (confirm('Delete this user')) {
      this.userService.deleteUser(id)
        .subscribe(() => {
          this.userDeleted = true;
          this.getAllUsers();
          setTimeout(() => {
            this.userDeleted = false;
          }, 3000);
        }, () => {
          this.loading = false;
          this.error = true;
        });
    }
  }

}
