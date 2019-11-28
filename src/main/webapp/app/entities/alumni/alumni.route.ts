import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Alumni, IAlumni } from 'app/shared/model/alumni.model';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AlumniDeletePopupComponent } from './alumni-delete-dialog.component';
import { AlumniDetailComponent } from './alumni-detail.component';
import { AlumniUpdateComponent } from './alumni-update.component';
import { AlumniComponent } from './alumni.component';
import { AlumniService } from './alumni.service';

@Injectable({ providedIn: 'root' })
export class AlumniResolve implements Resolve<IAlumni> {
  constructor(private service: AlumniService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAlumni> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((alumni: HttpResponse<Alumni>) => alumni.body));
    }
    return of(new Alumni());
  }
}

export const alumniRoute: Routes = [
  {
    path: '',
    component: AlumniComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI','ROLE_FACULTY'],
      defaultSort: 'id,asc',
      pageTitle: 'Alumni'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AlumniDetailComponent,
    resolve: {
      alumni: AlumniResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Alumni'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AlumniUpdateComponent,
    resolve: {
      alumni: AlumniResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Alumni'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AlumniUpdateComponent,
    resolve: {
      alumni: AlumniResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Alumni'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const alumniPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AlumniDeletePopupComponent,
    resolve: {
      alumni: AlumniResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Alumni'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
