import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IAspiredRole, AspiredRole } from 'app/shared/model/aspired-role.model';
import { AspiredRoleService } from './aspired-role.service';

@Component({
  selector: 'jhi-aspired-role-update',
  templateUrl: './aspired-role-update.component.html'
})
export class AspiredRoleUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    description: []
  });

  constructor(protected aspiredRoleService: AspiredRoleService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ aspiredRole }) => {
      this.updateForm(aspiredRole);
    });
  }

  updateForm(aspiredRole: IAspiredRole) {
    this.editForm.patchValue({
      id: aspiredRole.id,
      name: aspiredRole.name,
      description: aspiredRole.description
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const aspiredRole = this.createFromForm();
    if (aspiredRole.id !== undefined) {
      this.subscribeToSaveResponse(this.aspiredRoleService.update(aspiredRole));
    } else {
      this.subscribeToSaveResponse(this.aspiredRoleService.create(aspiredRole));
    }
  }

  private createFromForm(): IAspiredRole {
    return {
      ...new AspiredRole(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAspiredRole>>) {
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
