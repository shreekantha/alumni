import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { FundContributionUpdateComponent } from 'app/entities/fund-contribution/fund-contribution-update.component';
import { FundContributionService } from 'app/entities/fund-contribution/fund-contribution.service';
import { FundContribution } from 'app/shared/model/fund-contribution.model';

describe('Component Tests', () => {
  describe('FundContribution Management Update Component', () => {
    let comp: FundContributionUpdateComponent;
    let fixture: ComponentFixture<FundContributionUpdateComponent>;
    let service: FundContributionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [FundContributionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FundContributionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FundContributionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FundContributionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FundContribution('123');
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
        const entity = new FundContribution();
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
