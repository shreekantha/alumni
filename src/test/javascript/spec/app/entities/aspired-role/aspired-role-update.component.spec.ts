import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { AspiredRoleUpdateComponent } from 'app/entities/aspired-role/aspired-role-update.component';
import { AspiredRoleService } from 'app/entities/aspired-role/aspired-role.service';
import { AspiredRole } from 'app/shared/model/aspired-role.model';

describe('Component Tests', () => {
  describe('AspiredRole Management Update Component', () => {
    let comp: AspiredRoleUpdateComponent;
    let fixture: ComponentFixture<AspiredRoleUpdateComponent>;
    let service: AspiredRoleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [AspiredRoleUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AspiredRoleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AspiredRoleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AspiredRoleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AspiredRole('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new AspiredRole();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
