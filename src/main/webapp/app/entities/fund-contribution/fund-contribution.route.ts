import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { FundContribution, IFundContribution } from 'app/shared/model/fund-contribution.model';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FundContributionDeletePopupComponent } from './fund-contribution-delete-dialog.component';
import { FundContributionDetailComponent } from './fund-contribution-detail.component';
import { FundContributionPerFundraiserComponent } from './fund-contribution-per-fundraiser.component';
import { FundContributionUpdateComponent } from './fund-contribution-update.component';
import { FundContributionComponent } from './fund-contribution.component';
import { FundContributionService } from './fund-contribution.service';

@Injectable({ providedIn: 'root' })
export class FundContributionResolve implements Resolve<IFundContribution> {
  constructor(private service: FundContributionService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFundContribution> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((fundContribution: HttpResponse<FundContribution>) => fundContribution.body));
    }
    return of(new FundContribution());
  }
}

export const fundContributionRoute: Routes = [
  {
    path: '',
    component: FundContributionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      defaultSort: 'id,asc',
      pageTitle: 'FundContributions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'contributors',
    component: FundContributionPerFundraiserComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      defaultSort: 'id,asc',
      pageTitle: 'FundContributions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FundContributionDetailComponent,
    resolve: {
      fundContribution: FundContributionResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'FundContributions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FundContributionUpdateComponent,
    resolve: {
      fundContribution: FundContributionResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'FundContributions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FundContributionUpdateComponent,
    resolve: {
      fundContribution: FundContributionResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'FundContributions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const fundContributionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: FundContributionDeletePopupComponent,
    resolve: {
      fundContribution: FundContributionResolve
    },
    data: {
      authorities: ['ROLE_USER','ROLE_ALUMNI'],
      pageTitle: 'FundContributions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
