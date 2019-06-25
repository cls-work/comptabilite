import { Component, OnInit } from '@angular/core';
import {UserModel} from '../_models/user.model';
import {UserService} from '../_services/user.service';

@Component({
  selector: 'app-users-details',
  templateUrl: './users-details.component.html',
  styleUrls: ['./users-details.component.scss']
})
export class UsersDetailsComponent implements OnInit {
  users: UserModel[];
  loading: boolean;
  userDeleted: boolean;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers() {
    this.loading = true;
    this.userService.getAllUsers()
      .subscribe(users => {
        this.users = users;
        console.log(users);
        this.loading = false;
      });
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
        });
    }
  }

}
