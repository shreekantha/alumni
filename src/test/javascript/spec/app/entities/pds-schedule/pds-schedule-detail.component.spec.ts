import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { PdsScheduleDetailComponent } from 'app/entities/pds-schedule/pds-schedule-detail.component';
import { PdsSchedule } from 'app/shared/model/pds-schedule.model';

describe('Component Tests', () => {
  describe('PdsSchedule Management Detail Component', () => {
    let comp: PdsScheduleDetailComponent;
    let fixture: ComponentFixture<PdsScheduleDetailComponent>;
    const route = ({ data: of({ pdsSchedule: new PdsSchedule('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [PdsScheduleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PdsScheduleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PdsScheduleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.pdsSchedule).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
