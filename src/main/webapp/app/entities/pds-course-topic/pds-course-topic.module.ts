import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { PdsCourseTopicComponent } from './pds-course-topic.component';
import { PdsCourseTopicDetailComponent } from './pds-course-topic-detail.component';
import { PdsCourseTopicUpdateComponent } from './pds-course-topic-update.component';
import { PdsCourseTopicDeletePopupComponent, PdsCourseTopicDeleteDialogComponent } from './pds-course-topic-delete-dialog.component';
import { pdsCourseTopicRoute, pdsCourseTopicPopupRoute } from './pds-course-topic.route';

const ENTITY_STATES = [...pdsCourseTopicRoute, ...pdsCourseTopicPopupRoute];

@NgModule({
  imports: [DhiAlumniSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PdsCourseTopicComponent,
    PdsCourseTopicDetailComponent,
    PdsCourseTopicUpdateComponent,
    PdsCourseTopicDeleteDialogComponent,
    PdsCourseTopicDeletePopupComponent
  ],
  entryComponents: [PdsCourseTopicDeleteDialogComponent]
})
export class DhiAlumniPdsCourseTopicModule {}
