import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDegree, Degree } from 'app/shared/model/degree.model';
import { DegreeService } from './degree.service';

@Component({
  selector: 'jhi-degree-update',
  templateUrl: './degree-update.component.html'
})
export class DegreeUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    shortName: []
  });

  constructor(protected degreeService: DegreeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ degree }) => {
      this.updateForm(degree);
    });
  }

  updateForm(degree: IDegree) {
    this.editForm.patchValue({
      id: degree.id,
      name: degree.name,
      shortName: degree.shortName
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const degree = this.createFromForm();
    if (degree.id !== undefined) {
      this.subscribeToSaveResponse(this.degreeService.update(degree));
    } else {
      this.subscribeToSaveResponse(this.degreeService.create(degree));
    }
  }

  private createFromForm(): IDegree {
    return {
      ...new Degree(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      shortName: this.editForm.get(['shortName']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDegree>>) {
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
