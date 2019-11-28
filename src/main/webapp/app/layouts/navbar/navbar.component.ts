import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { VERSION } from 'app/app.constants';
import { AccountService } from 'app/core/auth/account.service';
import { LoginModalService } from 'app/core/login/login-modal.service';
import { LoginService } from 'app/core/login/login.service';
import { ProfileService } from 'app/layouts/profiles/profile.service';


@Component({
  selector: 'jhi-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['navbar.scss']
})
export class NavbarComponent implements OnInit {
  inProduction: boolean;
  isNavbarCollapsed: boolean;
  swaggerEnabled: boolean;
  modalRef: NgbModalRef;
  version: string;
  isOpen:boolean;
  name:any;

  constructor(
    private loginService: LoginService,
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private profileService: ProfileService,
    private router: Router
  ) {
    this.version = VERSION ? (VERSION.toLowerCase().startsWith('v') ? VERSION : 'v' + VERSION) : '';
    this.isNavbarCollapsed = true;
  }

  ngOnInit() {
    this.profileService.getProfileInfo().subscribe(profileInfo => {
      this.inProduction = profileInfo.inProduction;
      this.swaggerEnabled = profileInfo.swaggerEnabled;
    });
  }

  collapseNavbar() {
    this.isNavbarCollapsed = true;
  }

  isAuthenticated() {
    const loggedin = this.accountService.isAuthenticated();
    // if (!loggedin) {
    //   this.resetHeader();
    // } else {
    //   this.applyLeftNav();
    // }
   
    return loggedin;
  }

  login() {
    this.modalRef = this.loginModalService.open();
  }

  logout() {
    this.collapseNavbar();
    this.loginService.logout();
    this.router.navigate(['']);
    this.resetHeader();
  }

  toggleNavbar() {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }

  getImageUrl() {
    return this.isAuthenticated() ? this.accountService.getImageUrl() : null;
  }

  getUser(){
  return AccountService.getUsername();
  }

  getRole(){
    return AccountService.getRole();
  }
  
  private applyLeftNav() {
    // document.getElementById("main").classList.remove('ml-0');
    // document.getElementById("header").classList.add('header');
  }

  private resetHeader() {
    // document.getElementById("main").classList.add('ml-0');
    // document.getElementById("header").classList.remove('header');
  }


  public openOrCloseNav() {
    if (this.isOpen) {

      document.getElementById('left-sidenav').style.overflowY = "hidden";
      document.getElementById("left-sidenav").classList.remove('sidenav-expand');
      document.getElementById("left-sidenav").classList.add('sidenav-collapse');

      document.getElementById("main").classList.remove('wrapper-left');
      document.getElementById("main").classList.add('wrapper');

      document.getElementById('header').classList.remove('header-left');
      document.getElementById('header').classList.add('header');

      this.isOpen = false;
    } else {

      document.getElementById('left-sidenav').style.overflowY = "auto";
      document.getElementById("left-sidenav").classList.remove('sidenav-collapse');
      document.getElementById("left-sidenav").classList.add('sidenav-expand');

      document.getElementById("main").classList.remove('wrapper');
      document.getElementById("main").classList.add('wrapper-left');

      document.getElementById('header').classList.remove('header');
      document.getElementById('header').classList.add('header-left');

      this.isOpen = true;
    }
  }
}
