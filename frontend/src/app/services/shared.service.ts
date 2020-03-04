import { Injectable, EventEmitter } from '@angular/core';
import { User } from './../dto/user-dto';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  public static instance: SharedService = null;
  user: User;
  token: string;
  showTemplate = new EventEmitter<boolean>();

  constructor() {
    return SharedService.instance = SharedService.instance || this;
  }

  public static getInstance() {
    if (this.instance == null) {
      this.instance = new SharedService();
    }
    return this.instance;
  }

  public isLogged(): boolean {
    if (this.user == null) {
      return false;
    }
    return this.user.email !== '';
  }

}
