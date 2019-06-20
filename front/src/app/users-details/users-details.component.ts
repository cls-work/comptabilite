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

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getAllUsers()
      .subscribe(users=>{
        this.users=users;
        console.log(users)
      })
  }

}
