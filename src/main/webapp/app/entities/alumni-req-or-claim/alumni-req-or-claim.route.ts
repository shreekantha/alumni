import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { AlumniReqOrClaim, IAlumniReqOrClaim } from 'app/shared/model/alumni-req-or-claim.model';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AlumniReqOrClaimDeletePopupComponent } from './alumni-req-or-claim-delete-dialog.component';
import { AlumniReqOrClaimDetailComponent } from './alumni-req-or-claim-detail.component';
import { AlumniReqOrClaimUpdateComponent } from './alumni-req-or-claim-update.component';
import { AlumniReqOrClaimComponent } from './alumni-req-or-claim.component';
import { AlumniReqOrClaimService } from './alumni-req-or-claim.service';
import { AlumniReqOrClaimProcessComponent } from './process/alumni-req-or-claim-process.component';
import { AlumniReqOrClaimUpdateProcessComponent } from './process/alumni-req-or-claim-update-process.component';

@Injectable({ providedIn: 'root' })
export class AlumniReqOrClaimResolve implements Resolve<IAlumniReqOrClaim> {
  constructor(private service: AlumniReqOrClaimService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAlumniReqOrClaim> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((alumniReqOrClaim: HttpResponse<AlumniReqOrClaim>) => alumniReqOrClaim.body));
    }
    return of(new AlumniReqOrClaim());
  }
}

export const alumniReqOrClaimRoute: Routes = [
  {
    path: '',
    component: AlumniReqOrClaimComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      defaultSort: 'id,asc',
      pageTitle: 'AlumniReqOrClaims'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'process',
    component: AlumniReqOrClaimProcessComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ADMIN'],
      defaultSort: 'id,asc',
      pageTitle: 'My Reqs Or Claims'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AlumniReqOrClaimDetailComponent,
    resolve: {
      alumniReqOrClaim: AlumniReqOrClaimResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'AlumniReqOrClaims'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AlumniReqOrClaimUpdateComponent,
    resolve: {
      alumniReqOrClaim: AlumniReqOrClaimResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'AlumniReqOrClaims'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AlumniReqOrClaimUpdateComponent,
    resolve: {
      alumniReqOrClaim: AlumniReqOrClaimResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'AlumniReqOrClaims'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'process/:id/edit',
    component: AlumniReqOrClaimUpdateProcessComponent,
    resolve: {
      alumniReqOrClaim: AlumniReqOrClaimResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ADMIN'],
      pageTitle: 'AlumniReqOrClaims'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const alumniReqOrClaimPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AlumniReqOrClaimDeletePopupComponent,
    resolve: {
      alumniReqOrClaim: AlumniReqOrClaimResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'AlumniReqOrClaims'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
