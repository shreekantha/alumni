import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { PdsEnrollmentDetailComponent } from 'app/entities/pds-enrollment/pds-enrollment-detail.component';
import { PdsEnrollment } from 'app/shared/model/pds-enrollment.model';

describe('Component Tests', () => {
  describe('PdsEnrollment Management Detail Component', () => {
    let comp: PdsEnrollmentDetailComponent;
    let fixture: ComponentFixture<PdsEnrollmentDetailComponent>;
    const route = ({ data: of({ pdsEnrollment: new PdsEnrollment('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [PdsEnrollmentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PdsEnrollmentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PdsEnrollmentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.pdsEnrollment).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
