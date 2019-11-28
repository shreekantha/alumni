import { IPdsCourseTopic } from 'app/shared/model/pds-course-topic.model';

export interface IPdsCourse {
  id?: string;
  courseName?: string;
  description?: string;
  pdsCourseTopics?: IPdsCourseTopic[];
  profDevServiceName?: string;
  profDevServiceId?: string;
}

export class PdsCourse implements IPdsCourse {
  constructor(
    public id?: string,
    public courseName?: string,
    public description?: string,
    public pdsCourseTopics?: IPdsCourseTopic[],
    public profDevServiceName?: string,
    public profDevServiceId?: string
  ) {}
}
