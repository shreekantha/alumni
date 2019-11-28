import { Moment } from 'moment';
import { IFundContribution } from 'app/shared/model/fund-contribution.model';
import { FundraiseStatus } from 'app/shared/model/enumerations/fundraise-status.model';

export interface IFundraiser {
  id?: string;
  name?: string;
  description?: string;
  targetAmount?: number;
  collectedAmount?: number;
  lastDateToContribute?: Moment;
  status?: FundraiseStatus;
  fundContributions?: IFundContribution[];
}

export class Fundraiser implements IFundraiser {
  constructor(
    public id?: string,
    public name?: string,
    public description?: string,
    public targetAmount?: number,
    public collectedAmount?: number,
    public lastDateToContribute?: Moment,
    public status?: FundraiseStatus,
    public fundContributions?: IFundContribution[]
  ) {}
}
