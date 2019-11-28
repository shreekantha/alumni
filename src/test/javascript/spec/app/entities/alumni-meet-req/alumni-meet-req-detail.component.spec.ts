import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { AlumniMeetReqDetailComponent } from 'app/entities/alumni-meet-req/alumni-meet-req-detail.component';
import { AlumniMeetReq } from 'app/shared/model/alumni-meet-req.model';

describe('Component Tests', () => {
  describe('AlumniMeetReq Management Detail Component', () => {
    let comp: AlumniMeetReqDetailComponent;
    let fixture: ComponentFixture<AlumniMeetReqDetailComponent>;
    const route = ({ data: of({ alumniMeetReq: new AlumniMeetReq('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [AlumniMeetReqDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AlumniMeetReqDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlumniMeetReqDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.alumniMeetReq).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
