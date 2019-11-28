// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AccountService } from 'app/core/auth/account.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { AspiredRoleService } from 'app/entities/aspired-role/aspired-role.service';
import { MeetReqTopicService } from 'app/entities/meet-req-topic/meet-req-topic.service';
import { AlumniMeetReq, IAlumniMeetReq } from 'app/shared/model/alumni-meet-req.model';
import { IAspiredRole } from 'app/shared/model/aspired-role.model';
import { AlumniMeetReqStatus } from 'app/shared/model/enumerations/alumni-meet-req-status.model';
import { IMeetReqTopic } from 'app/shared/model/meet-req-topic.model';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { Observable } from 'rxjs';
import { AlumniMeetReqService } from './alumni-meet-req.service';

@Component({
  selector: 'jhi-alumni-meet-req-update',
  templateUrl: './alumni-meet-req-update.component.html'
})
export class AlumniMeetReqUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  meetreqtopics: IMeetReqTopic[];

  aspiredroles: IAspiredRole[];

  editForm = this.fb.group({
    id: [],
    aboutMe: [],
    description: [],
    document: [],
    documentContentType: [],
    status: [],
    requestById: [],
    requestToId: [null, Validators.required],
    topicId: [null, Validators.required],
    aspiredRoleId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected alumniMeetReqService: AlumniMeetReqService,
    protected userService: UserService,
    protected meetReqTopicService: MeetReqTopicService,
    protected aspiredRoleService: AspiredRoleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ alumniMeetReq }) => {
      this.updateForm(alumniMeetReq);
    });
    this.userService
      .query({
        'role':'ROLE_ALUMNI'
      })
      .subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.meetReqTopicService
      .query()
      .subscribe(
        (res: HttpResponse<IMeetReqTopic[]>) => (this.meetreqtopics = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.aspiredRoleService
      .query()
      .subscribe(
        (res: HttpResponse<IAspiredRole[]>) => (this.aspiredroles = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(alumniMeetReq: IAlumniMeetReq) {
    alumniMeetReq.requestById=AccountService.getUserId();
    this.editForm.patchValue({
      id: alumniMeetReq.id,
      aboutMe: alumniMeetReq.aboutMe,
      description: alumniMeetReq.description,
      document: alumniMeetReq.document,
      documentContentType: alumniMeetReq.documentContentType,
      status: alumniMeetReq.status,
      requestById: alumniMeetReq.requestById,
      requestToId: alumniMeetReq.requestToId,
      topicId: alumniMeetReq.topicId,
      aspiredRoleId: alumniMeetReq.aspiredRoleId
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file: File = event.target.files[0];
        if (isImage && !file.type.startsWith('image/')) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      // eslint-disable-next-line no-console
      () => console.log('blob added'), // success
      this.onError
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const alumniMeetReq = this.createFromForm();
    if (alumniMeetReq.id !== undefined) {
      this.subscribeToSaveResponse(this.alumniMeetReqService.update(alumniMeetReq));
    } else {
      alumniMeetReq.status=AlumniMeetReqStatus.REQUESTED;
      alumniMeetReq.requestById=AccountService.getUserId();
      this.subscribeToSaveResponse(this.alumniMeetReqService.create(alumniMeetReq));
    }
  }

  private createFromForm(): IAlumniMeetReq {
    return {
      ...new AlumniMeetReq(),
      id: this.editForm.get(['id']).value,
      aboutMe: this.editForm.get(['aboutMe']).value,
      description: this.editForm.get(['description']).value,
      documentContentType: this.editForm.get(['documentContentType']).value,
      document: this.editForm.get(['document']).value,
      status: this.editForm.get(['status']).value,
      requestById: this.editForm.get(['requestById']).value,
      requestToId: this.editForm.get(['requestToId']).value,
      topicId: this.editForm.get(['topicId']).value,
      aspiredRoleId: this.editForm.get(['aspiredRoleId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAlumniMeetReq>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackUserById(index: number, item: IUser) {
    return item.id;
  }

  trackMeetReqTopicById(index: number, item: IMeetReqTopic) {
    return item.id;
  }

  trackAspiredRoleById(index: number, item: IAspiredRole) {
    return item.id;
  }
}
