import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { ReqOrClaimSubjectComponent } from './req-or-claim-subject.component';
import { ReqOrClaimSubjectDetailComponent } from './req-or-claim-subject-detail.component';
import { ReqOrClaimSubjectUpdateComponent } from './req-or-claim-subject-update.component';
import {
  ReqOrClaimSubjectDeletePopupComponent,
  ReqOrClaimSubjectDeleteDialogComponent
} from './req-or-claim-subject-delete-dialog.component';
import { reqOrClaimSubjectRoute, reqOrClaimSubjectPopupRoute } from './req-or-claim-subject.route';

const ENTITY_STATES = [...reqOrClaimSubjectRoute, ...reqOrClaimSubjectPopupRoute];

@NgModule({
  imports: [DhiAlumniSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ReqOrClaimSubjectComponent,
    ReqOrClaimSubjectDetailComponent,
    ReqOrClaimSubjectUpdateComponent,
    ReqOrClaimSubjectDeleteDialogComponent,
    ReqOrClaimSubjectDeletePopupComponent
  ],
  entryComponents: [ReqOrClaimSubjectDeleteDialogComponent]
})
export class DhiAlumniReqOrClaimSubjectModule {}
