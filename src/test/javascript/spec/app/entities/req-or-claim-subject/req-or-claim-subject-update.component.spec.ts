import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { ReqOrClaimSubjectUpdateComponent } from 'app/entities/req-or-claim-subject/req-or-claim-subject-update.component';
import { ReqOrClaimSubjectService } from 'app/entities/req-or-claim-subject/req-or-claim-subject.service';
import { ReqOrClaimSubject } from 'app/shared/model/req-or-claim-subject.model';

describe('Component Tests', () => {
  describe('ReqOrClaimSubject Management Update Component', () => {
    let comp: ReqOrClaimSubjectUpdateComponent;
    let fixture: ComponentFixture<ReqOrClaimSubjectUpdateComponent>;
    let service: ReqOrClaimSubjectService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [ReqOrClaimSubjectUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ReqOrClaimSubjectUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReqOrClaimSubjectUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReqOrClaimSubjectService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ReqOrClaimSubject('123');
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
        const entity = new ReqOrClaimSubject();
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
