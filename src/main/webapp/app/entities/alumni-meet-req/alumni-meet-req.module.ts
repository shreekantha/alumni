import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { AlumniMeetReqDeleteDialogComponent, AlumniMeetReqDeletePopupComponent } from './alumni-meet-req-delete-dialog.component';
import { AlumniMeetReqDetailComponent } from './alumni-meet-req-detail.component';
import { AlumniMeetReqProcessComponent } from './alumni-meet-req-process.component';
import { AlumniMeetReqUpdateComponent } from './alumni-meet-req-update.component';
import { AlumniMeetReqComponent } from './alumni-meet-req.component';
import { alumniMeetReqPopupRoute, alumniMeetReqRoute } from './alumni-meet-req.route';


const ENTITY_STATES = [...alumniMeetReqRoute, ...alumniMeetReqPopupRoute];

@NgModule({
  imports: [DhiAlumniSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AlumniMeetReqComponent,
    AlumniMeetReqDetailComponent,
    AlumniMeetReqUpdateComponent,
    AlumniMeetReqDeleteDialogComponent,
    AlumniMeetReqDeletePopupComponent,
    AlumniMeetReqProcessComponent
  ],
  entryComponents: [AlumniMeetReqDeleteDialogComponent]
})
export class DhiAlumniAlumniMeetReqModule {}
