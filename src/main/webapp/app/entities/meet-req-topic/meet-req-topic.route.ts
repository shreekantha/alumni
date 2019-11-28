import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MeetReqTopic } from 'app/shared/model/meet-req-topic.model';
import { MeetReqTopicService } from './meet-req-topic.service';
import { MeetReqTopicComponent } from './meet-req-topic.component';
import { MeetReqTopicDetailComponent } from './meet-req-topic-detail.component';
import { MeetReqTopicUpdateComponent } from './meet-req-topic-update.component';
import { MeetReqTopicDeletePopupComponent } from './meet-req-topic-delete-dialog.component';
import { IMeetReqTopic } from 'app/shared/model/meet-req-topic.model';

@Injectable({ providedIn: 'root' })
export class MeetReqTopicResolve implements Resolve<IMeetReqTopic> {
  constructor(private service: MeetReqTopicService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMeetReqTopic> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((meetReqTopic: HttpResponse<MeetReqTopic>) => meetReqTopic.body));
    }
    return of(new MeetReqTopic());
  }
}

export const meetReqTopicRoute: Routes = [
  {
    path: '',
    component: MeetReqTopicComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MeetReqTopics'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MeetReqTopicDetailComponent,
    resolve: {
      meetReqTopic: MeetReqTopicResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MeetReqTopics'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MeetReqTopicUpdateComponent,
    resolve: {
      meetReqTopic: MeetReqTopicResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MeetReqTopics'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MeetReqTopicUpdateComponent,
    resolve: {
      meetReqTopic: MeetReqTopicResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MeetReqTopics'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const meetReqTopicPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MeetReqTopicDeletePopupComponent,
    resolve: {
      meetReqTopic: MeetReqTopicResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MeetReqTopics'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
