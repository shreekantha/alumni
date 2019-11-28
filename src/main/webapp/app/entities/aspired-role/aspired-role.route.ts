import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AspiredRole } from 'app/shared/model/aspired-role.model';
import { AspiredRoleService } from './aspired-role.service';
import { AspiredRoleComponent } from './aspired-role.component';
import { AspiredRoleDetailComponent } from './aspired-role-detail.component';
import { AspiredRoleUpdateComponent } from './aspired-role-update.component';
import { AspiredRoleDeletePopupComponent } from './aspired-role-delete-dialog.component';
import { IAspiredRole } from 'app/shared/model/aspired-role.model';

@Injectable({ providedIn: 'root' })
export class AspiredRoleResolve implements Resolve<IAspiredRole> {
  constructor(private service: AspiredRoleService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAspiredRole> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((aspiredRole: HttpResponse<AspiredRole>) => aspiredRole.body));
    }
    return of(new AspiredRole());
  }
}

export const aspiredRoleRoute: Routes = [
  {
    path: '',
    component: AspiredRoleComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'AspiredRoles'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AspiredRoleDetailComponent,
    resolve: {
      aspiredRole: AspiredRoleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'AspiredRoles'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AspiredRoleUpdateComponent,
    resolve: {
      aspiredRole: AspiredRoleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'AspiredRoles'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AspiredRoleUpdateComponent,
    resolve: {
      aspiredRole: AspiredRoleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'AspiredRoles'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const aspiredRolePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AspiredRoleDeletePopupComponent,
    resolve: {
      aspiredRole: AspiredRoleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'AspiredRoles'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
