import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { MeetReqTopicDetailComponent } from 'app/entities/meet-req-topic/meet-req-topic-detail.component';
import { MeetReqTopic } from 'app/shared/model/meet-req-topic.model';

describe('Component Tests', () => {
  describe('MeetReqTopic Management Detail Component', () => {
    let comp: MeetReqTopicDetailComponent;
    let fixture: ComponentFixture<MeetReqTopicDetailComponent>;
    const route = ({ data: of({ meetReqTopic: new MeetReqTopic('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [MeetReqTopicDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MeetReqTopicDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MeetReqTopicDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.meetReqTopic).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
