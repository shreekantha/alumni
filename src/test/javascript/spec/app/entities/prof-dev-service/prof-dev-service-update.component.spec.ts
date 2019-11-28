import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { ProfDevServiceUpdateComponent } from 'app/entities/prof-dev-service/prof-dev-service-update.component';
import { ProfDevServiceService } from 'app/entities/prof-dev-service/prof-dev-service.service';
import { ProfDevService } from 'app/shared/model/prof-dev-service.model';

describe('Component Tests', () => {
  describe('ProfDevService Management Update Component', () => {
    let comp: ProfDevServiceUpdateComponent;
    let fixture: ComponentFixture<ProfDevServiceUpdateComponent>;
    let service: ProfDevServiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [ProfDevServiceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProfDevServiceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProfDevServiceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProfDevServiceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProfDevService('123');
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
        const entity = new ProfDevService();
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
