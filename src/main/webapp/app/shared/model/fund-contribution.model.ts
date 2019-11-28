import { Moment } from 'moment';
import { Currency } from 'app/shared/model/enumerations/currency.model';

export interface IFundContribution {
  id?: string;
  currency?: Currency;
  contrAmount?: number;
  contrDate?: Moment;
  contibutorFirstName?: string;
  contibutorId?: string;
  fundraiserName?: string;
  fundraiserId?: string;
}

export class FundContribution implements IFundContribution {
  constructor(
    public id?: string,
    public currency?: Currency,
    public contrAmount?: number,
    public contrDate?: Moment,
    public contibutorFirstName?: string,
    public contibutorId?: string,
    public fundraiserName?: string,
    public fundraiserId?: string
  ) {}
}
