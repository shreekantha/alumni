import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DhiAlumniTestModule } from '../../../test.module';
import { PdsCourseTopicDetailComponent } from 'app/entities/pds-course-topic/pds-course-topic-detail.component';
import { PdsCourseTopic } from 'app/shared/model/pds-course-topic.model';

describe('Component Tests', () => {
  describe('PdsCourseTopic Management Detail Component', () => {
    let comp: PdsCourseTopicDetailComponent;
    let fixture: ComponentFixture<PdsCourseTopicDetailComponent>;
    const route = ({ data: of({ pdsCourseTopic: new PdsCourseTopic('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DhiAlumniTestModule],
        declarations: [PdsCourseTopicDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PdsCourseTopicDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PdsCourseTopicDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.pdsCourseTopic).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
