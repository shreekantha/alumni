import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { MeetReqTopicComponent } from './meet-req-topic.component';
import { MeetReqTopicDetailComponent } from './meet-req-topic-detail.component';
import { MeetReqTopicUpdateComponent } from './meet-req-topic-update.component';
import { MeetReqTopicDeletePopupComponent, MeetReqTopicDeleteDialogComponent } from './meet-req-topic-delete-dialog.component';
import { meetReqTopicRoute, meetReqTopicPopupRoute } from './meet-req-topic.route';

const ENTITY_STATES = [...meetReqTopicRoute, ...meetReqTopicPopupRoute];

@NgModule({
  imports: [DhiAlumniSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MeetReqTopicComponent,
    MeetReqTopicDetailComponent,
    MeetReqTopicUpdateComponent,
    MeetReqTopicDeleteDialogComponent,
    MeetReqTopicDeletePopupComponent
  ],
  entryComponents: [MeetReqTopicDeleteDialogComponent]
})
export class DhiAlumniMeetReqTopicModule {}
