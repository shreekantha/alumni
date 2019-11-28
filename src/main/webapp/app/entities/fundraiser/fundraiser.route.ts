import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Fundraiser, IFundraiser } from 'app/shared/model/fundraiser.model';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FundraiserDeletePopupComponent } from './fundraiser-delete-dialog.component';
import { FundraiserDetailComponent } from './fundraiser-detail.component';
import { FundraiserUpdateComponent } from './fundraiser-update.component';
import { FundraiserComponent } from './fundraiser.component';
import { FundraiserService } from './fundraiser.service';

@Injectable({ providedIn: 'root' })
export class FundraiserResolve implements Resolve<IFundraiser> {
  constructor(private service: FundraiserService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFundraiser> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((fundraiser: HttpResponse<Fundraiser>) => fundraiser.body));
    }
    return of(new Fundraiser());
  }
}

export const fundraiserRoute: Routes = [
  {
    path: '',
    component: FundraiserComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      defaultSort: 'id,asc',
      pageTitle: 'Fundraisers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FundraiserDetailComponent,
    resolve: {
      fundraiser: FundraiserResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'Fundraisers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FundraiserUpdateComponent,
    resolve: {
      fundraiser: FundraiserResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'Fundraisers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FundraiserUpdateComponent,
    resolve: {
      fundraiser: FundraiserResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'Fundraisers'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const fundraiserPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: FundraiserDeletePopupComponent,
    resolve: {
      fundraiser: FundraiserResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'Fundraisers'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
