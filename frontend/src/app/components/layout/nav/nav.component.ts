import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SharedService } from 'src/app/services/shared.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {
  shared: SharedService;

  constructor(private router: Router) {
    this.shared = SharedService.getInstance();
  }

  ngOnInit() {

  }

  toggleSideMenu() {
    const element: HTMLElement = document.body.querySelector('.mdl-layout__drawer-button') as HTMLElement;
    element.click();
  }


  signOut(): void {
    this.shared.token = null;
    this.shared.user = null;
    window.location.href = '/login';
    window.location.reload();
 }
}
