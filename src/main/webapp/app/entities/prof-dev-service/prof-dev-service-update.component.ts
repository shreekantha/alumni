import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IProfDevService, ProfDevService } from 'app/shared/model/prof-dev-service.model';
import { ProfDevServiceService } from './prof-dev-service.service';

@Component({
  selector: 'jhi-prof-dev-service-update',
  templateUrl: './prof-dev-service-update.component.html'
})
export class ProfDevServiceUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    description: []
  });

  constructor(protected profDevServiceService: ProfDevServiceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ profDevService }) => {
      this.updateForm(profDevService);
    });
  }

  updateForm(profDevService: IProfDevService) {
    this.editForm.patchValue({
      id: profDevService.id,
      name: profDevService.name,
      description: profDevService.description
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const profDevService = this.createFromForm();
    if (profDevService.id !== undefined) {
      this.subscribeToSaveResponse(this.profDevServiceService.update(profDevService));
    } else {
      this.subscribeToSaveResponse(this.profDevServiceService.create(profDevService));
    }
  }

  private createFromForm(): IProfDevService {
    return {
      ...new ProfDevService(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProfDevService>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
