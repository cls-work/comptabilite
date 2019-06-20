import {Component, Input, OnInit} from '@angular/core';
import {UserModel} from '../_models/user.model';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {

  @Input() user: UserModel;
  constructor() { }

  ngOnInit() {
  }

  deleteUser(id: string) {
    console.log(id);
  }
}
