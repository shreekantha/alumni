import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { DhiAlumniSharedModule } from 'app/shared/shared.module';
import { AlumniReqOrClaimDeleteDialogComponent, AlumniReqOrClaimDeletePopupComponent } from './alumni-req-or-claim-delete-dialog.component';
import { AlumniReqOrClaimDetailComponent } from './alumni-req-or-claim-detail.component';
import { AlumniReqOrClaimUpdateComponent } from './alumni-req-or-claim-update.component';
import { AlumniReqOrClaimComponent } from './alumni-req-or-claim.component';
import { alumniReqOrClaimPopupRoute, alumniReqOrClaimRoute } from './alumni-req-or-claim.route';
import { AlumniReqOrClaimProcessComponent } from './process/alumni-req-or-claim-process.component';
import { AlumniReqOrClaimUpdateProcessComponent } from './process/alumni-req-or-claim-update-process.component';


const ENTITY_STATES = [...alumniReqOrClaimRoute, ...alumniReqOrClaimPopupRoute];

@NgModule({
  imports: [DhiAlumniSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AlumniReqOrClaimComponent,
    AlumniReqOrClaimDetailComponent,
    AlumniReqOrClaimUpdateComponent,
    AlumniReqOrClaimDeleteDialogComponent,
    AlumniReqOrClaimDeletePopupComponent,
    AlumniReqOrClaimProcessComponent,
    AlumniReqOrClaimUpdateProcessComponent
  ],
  entryComponents: [AlumniReqOrClaimDeleteDialogComponent]
})
export class DhiAlumniAlumniReqOrClaimModule {}
