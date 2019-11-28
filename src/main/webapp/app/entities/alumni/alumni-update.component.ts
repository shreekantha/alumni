import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IAlumni, Alumni } from 'app/shared/model/alumni.model';
import { AlumniService } from './alumni.service';

@Component({
  selector: 'jhi-alumni-update',
  templateUrl: './alumni-update.component.html'
})
export class AlumniUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    usn: [],
    name: [],
    email: [],
    mobile: [],
    degree: [],
    specialization: [],
    jobPosition: [],
    company: [],
    yearOfGraduation: [],
    experience: []
  });

  constructor(protected alumniService: AlumniService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ alumni }) => {
      this.updateForm(alumni);
    });
  }

  updateForm(alumni: IAlumni) {
    this.editForm.patchValue({
      id: alumni.id,
      usn: alumni.usn,
      name: alumni.name,
      email: alumni.email,
      mobile: alumni.mobile,
      degree: alumni.degree,
      specialization: alumni.specialization,
      jobPosition: alumni.jobPosition,
      company: alumni.company,
      yearOfGraduation: alumni.yearOfGraduation,
      experience: alumni.experience
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const alumni = this.createFromForm();
    if (alumni.id !== undefined) {
      this.subscribeToSaveResponse(this.alumniService.update(alumni));
    } else {
      this.subscribeToSaveResponse(this.alumniService.create(alumni));
    }
  }

  private createFromForm(): IAlumni {
    return {
      ...new Alumni(),
      id: this.editForm.get(['id']).value,
      usn: this.editForm.get(['usn']).value,
      name: this.editForm.get(['name']).value,
      email: this.editForm.get(['email']).value,
      mobile: this.editForm.get(['mobile']).value,
      degree: this.editForm.get(['degree']).value,
      specialization: this.editForm.get(['specialization']).value,
      jobPosition: this.editForm.get(['jobPosition']).value,
      company: this.editForm.get(['company']).value,
      yearOfGraduation: this.editForm.get(['yearOfGraduation']).value,
      experience: this.editForm.get(['experience']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAlumni>>) {
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
