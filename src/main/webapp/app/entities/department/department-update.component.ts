import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IDepartment, Department } from 'app/shared/model/department.model';
import { DepartmentService } from './department.service';
import { IDegree } from 'app/shared/model/degree.model';
import { DegreeService } from 'app/entities/degree/degree.service';

@Component({
  selector: 'jhi-department-update',
  templateUrl: './department-update.component.html'
})
export class DepartmentUpdateComponent implements OnInit {
  isSaving: boolean;

  degrees: IDegree[];

  editForm = this.fb.group({
    id: [],
    name: [],
    shortName: [],
    degreeId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected departmentService: DepartmentService,
    protected degreeService: DegreeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ department }) => {
      this.updateForm(department);
    });
    this.degreeService
      .query()
      .subscribe((res: HttpResponse<IDegree[]>) => (this.degrees = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(department: IDepartment) {
    this.editForm.patchValue({
      id: department.id,
      name: department.name,
      shortName: department.shortName,
      degreeId: department.degreeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const department = this.createFromForm();
    if (department.id !== undefined) {
      this.subscribeToSaveResponse(this.departmentService.update(department));
    } else {
      this.subscribeToSaveResponse(this.departmentService.create(department));
    }
  }

  private createFromForm(): IDepartment {
    return {
      ...new Department(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      shortName: this.editForm.get(['shortName']).value,
      degreeId: this.editForm.get(['degreeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepartment>>) {
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

  trackDegreeById(index: number, item: IDegree) {
    return item.id;
  }
}
